/**
 * Utility to control STIXcontrol devices.
 *
 * @author mino
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdint.h> 
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <errno.h>
#include <unistd.h>
#include "i2c-dev.h"

#define I2CBUS_NAME	"/dev/i2c-3"

#define RELAY0_MODE	1
#define RELAY1_MODE	2
#define VOLTS0_MODE	3
#define VOLTS1_MODE	4
#define AMPS0_MODE	5
#define AMPS1_MODE	6
#define AMPS2_MODE	7

#define NUM_READINGS	7

void printusage();
void dec2bin(long decimal, char *binary);

int main(int argc, char *argv[]) {
	int fh;
	uint8_t stixbus_address = 0;
	uint8_t operating_mode = 0;
	uint8_t debug_mode = 0;
	int c, i;
	uint8_t data[1];
	char binary[9];
	float reading;

	//--- Parsing the input:
	while ((c = getopt (argc, argv, "r:v:a:d")) != -1) {
		switch(c) {
			case 'r':
				if(atoi(optarg) == 0)
					operating_mode = RELAY0_MODE;
				else if(atoi(optarg) == 1)
					operating_mode = RELAY1_MODE;
				else {
					fprintf(stderr, "ERROR: -r requires argument 0 or 1\n\n");
					printusage();
					exit(-1);
				}
				break;

			case 'v':
				if(atoi(optarg) == 0)
					operating_mode = VOLTS0_MODE;
				else if(atoi(optarg) == 1)
					operating_mode = VOLTS1_MODE;
				else {
					fprintf(stderr, "ERROR: -v requires argument 0 or 1\n\n");
					printusage();
					exit(-1);
				}
				break;

			case 'a':
				if(atoi(optarg) == 0)
					operating_mode = AMPS0_MODE;
				else if(atoi(optarg) == 1)
					operating_mode = AMPS1_MODE;
				else if(atoi(optarg) == 2)
					operating_mode = AMPS2_MODE;
				else {
					fprintf(stderr, "ERROR: -a requires argument 0, 1 or 2\n\n");
					printusage();
					exit(-1);
				}
				break;

			case 'd':
				debug_mode = 1;
				printf("DEBUG: Debug mode enabled\n");
				break;

			case '?':
				if (optopt == 'r' || optopt == 'v' || optopt == 'a') {
					fprintf (stderr, "ERROR: option -%c requires an argument.\n\n", optopt);
					printusage();
				} else if (isprint (optopt)) {
					fprintf (stderr, "ERROR: unknown option `-%c'.\n\n", optopt);
					printusage();
				} else {
					fprintf (stderr, "ERROR: unknown option character `\\x%x'.\n\n", optopt);
					printusage();
				}
				break;

			default:
				fprintf(stderr, "ERROR: unrecognized option\n\n");
				printusage();
				exit(-1);
		}
	}
	if(debug_mode) printf("DEBUG: Operating_mode: %d\n", operating_mode);

	if((argc - optind) != 1) {
		fprintf (stderr, "ERROR: stixbus_address not specified.\n\n");
		printusage();
		exit(-1);
    	}
	sscanf(argv[optind], "%d", &stixbus_address);
	if(stixbus_address < 0 || stixbus_address > 8) {
		fprintf (stderr, "ERROR: stixbus_address not valid.\n\n");
		printusage();
		exit(-1);
	}
	stixbus_address = 72 + stixbus_address; // the PCF8591 chip we use has adds a fixed offset of 0x48 to the I2C slave address.

	//--- Opening bus:
	if(debug_mode) printf("DEBUG: Opening I2C bus '%s'\n", I2CBUS_NAME);
	fh = open(I2CBUS_NAME, O_RDWR);
	if(fh < 0) {
		fprintf(stderr, "ERROR while opening the I2C bus: %s\n",strerror(errno));
		exit(-1);
	}

	//--- Selecting the slave:
	if(debug_mode) printf("DEBUG: Selecting device at address 0x%x\n", stixbus_address);
	if(ioctl(fh, I2C_SLAVE, stixbus_address) < 0) {
		fprintf(stderr, "ERROR while selecting the slave device: %s\n",strerror(errno));
		exit(-1);
	}

	//--- Writing two bytes on the bus:
	switch(operating_mode) {
		case RELAY0_MODE:
			data[0] = 0x40;	// analog output enabled
			data[1] = 0x00; // zero voltage on the analog output
			if(debug_mode) {
				printf("DEBUG: Writing 2 bytes: ");
				dec2bin(data[0], binary); printf("[%s] ", binary);
				dec2bin(data[1], binary); printf("[%s]\n", binary);
			}
			write(fh, data, 2);
			break;

		case RELAY1_MODE:
			data[0] = 0x40;	// analog output enabled
			data[1] = 0xFF; // maximum voltage on the analog output
			if(debug_mode) {
				printf("DEBUG: Writing 2 bytes: ");
				dec2bin(data[0], binary); printf("[%s] ", binary);
				dec2bin(data[1], binary); printf("[%s]\n", binary);
			}
			write(fh, data, 2);
			break;

		case VOLTS0_MODE:
		case VOLTS1_MODE:
			data[0] = 0x40;	// selecting ADC0, analog output disabled
			if(debug_mode) {
				printf("DEBUG: Writing 1 byte: ");
				dec2bin(data[0], binary); printf("[%s]\n", binary);
			}
			write(fh, data, 1);
			break;

		case AMPS0_MODE:
		case AMPS1_MODE:
		case AMPS2_MODE:
			data[0] = 0x41;	// selecting ADC1, analog output disabled
			if(debug_mode) {
				printf("DEBUG: Writing 1 byte: ");
				dec2bin(data[0], binary); printf("[%s]\n", binary);
			}
			write(fh, data, 1);
			break;

		default:
			fprintf(stderr, "ERROR: operating mode not valid.\n");
			exit(-1);
	}

	//--- If needed, we read the data back from the bus:
	if(operating_mode != RELAY0_MODE && operating_mode != RELAY1_MODE) {

		//--- Throw away the first A/D sampling, as it is often stale:
		if(debug_mode) printf("DEBUG: Discarding 1 byte: ");
		read(fh, data, 1);
		if(debug_mode) printf("[hex: 0x%x - dec: %d]\n", data[0], data[0]);
	
		//--- We perform NUM_READINGS readings and average them, then we
		//--- decode and print the response to stdout:
		reading = 0;
		for(i = 0; i < NUM_READINGS; i++) {
			read(fh, data, 1);
			if(debug_mode) printf("DEBUG: Reading 1 byte: [hex: 0x%x - dec: %d]\n", data[0], data[0]);
			reading += data[0];
		}
		reading = reading / (double) NUM_READINGS; // averaged value
	}

	switch(operating_mode) {
		case VOLTS0_MODE: // 0-16V
			reading = reading / 15.402971;
			printf("%f\n", reading);
			break;

		case VOLTS1_MODE: // 0-22V
			reading = reading / 11.144043;
			printf("%f\n", reading);
			break;

		case AMPS0_MODE: // 0-6A
			reading = (reading - 9) / 38.383333;
			if(reading < 0) reading = 0;
			printf("%f\n", reading);
			break;

		case AMPS1_MODE: // 0-9A
			reading = (reading - 9) / 25.875843;
			if(reading < 0) reading = 0;
			printf("%f\n", reading);
			break;

		case AMPS2_MODE: //0-12A
			reading = (reading - 9) / 12.452540;
			if(reading < 0) reading = 0;
			printf("%f\n", reading);
			break;
	}

	//--- Closing the bus:
	if(debug_mode) printf("DEBUG: End\n");
	close(fh);
}

// Shows the program help in stdout:
void printusage() {
       	printf("Usage: \n");
	printf("\tstixcontrol [stixbus_address] [[-r 0|1] | [-v 0|1 ] | [-a 0|1|2 ]] [-d]\n\n");
	printf("Options:\n");
	printf("\tstixbus_address: index on the stixbus, from 0 to 8\n");
	printf("\t-r: set the relay to on (1) or off (0)\n");
	printf("\t-v: prints the voltage level on stdout. Two configurations are accepted: 0 (0-16V) and 1 (0-22V)\n");
	printf("\t-a: prints the current level on stdout. Three configurations are accepted: 0 (0-6A), 1 (0-9A), 2 (0-19A)\n");
	printf("\t-d: turns on debug mode\n\n");
	printf("Notes:\n");
	printf("\t- Only one flag among -r, -v and -a can be specified at any time.\n\n");
}

// Transform a decimal number to a string of zeros/ones, for debug:
void dec2bin(long decimal, char *binary) {
	int  k = 0, n = 0;
	int  neg_flag = 0;
	int  remain;
	char temp[9];

	do {
		remain    = decimal % 2;
		decimal   = decimal / 2;
		temp[k++] = remain + '0';
	} while (decimal > 0);

	// fills the byte with zeros:
	while(k < 8)
		temp[k++] = '0';

	// reverse the spelling:
	for(k = 0; k < 8; k++)
		binary[k] = temp[7-k];

	// terminate the string:
	binary[8] = 0;
}
