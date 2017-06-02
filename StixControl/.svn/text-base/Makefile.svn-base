CC = gcc
CFLAGS =
BUILDLIBS =

TARGET = stixcontrol

OBJS = stixcontrol.o

${TARGET}: $(OBJS)
	${CC} ${CFLAGS} ${OBJS} -o ${TARGET}

clean:
	rm ${TARGET} ${OBJS}

install:
	cp ${TARGET} /usr/bin/
