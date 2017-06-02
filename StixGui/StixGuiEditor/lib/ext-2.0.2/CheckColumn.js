Ext.ns('Ext.ux.grid');
Ext.ux.grid.CheckColumn = function(config){
    Ext.apply(this, config);
    if(!this.id){
        this.id = Ext.id();
    }
    this.renderer = this.renderer.createDelegate(this);
    
    //Tag the object as a checkColumn
    this.checkColumn = true;
};

Ext.ux.grid.CheckColumn.prototype ={
    init : function(grid){
        this.grid = grid;
        this.grid.on('render', function()
        {
            var view = this.grid.getView();
            view.mainBody.on('mousedown', this.onMouseDown, this);
            //Add the keypress to the grid to monitor keys
            this.grid.on('keypress', this.onKeyPress);
        }, this);
    },

    createEditEvent: function(aGrid, aRecord, aRowIdx, aColumn, aNewVal, aOriVal)
    {
        var editEvent =
            {
                grid: aGrid,
                record: aGrid.store.getAt(aRowIdx),
                field: aColumn.dataIndex,
                value: aNewVal,
                originalValue: aOriVal,
                row: aRowIdx,
                column: aColumn
            };
        return editEvent;
    },

    onMouseDown : function(e, t)
    {
        if(t.className && t.className.indexOf('x-grid3-cc-'+this.id) != -1)
        {
            e.stopEvent();
            var index    = this.grid.getView().findRowIndex(t);
            var record   = this.grid.store.getAt(index);
            var colModel = this.grid.getColumnModel();
            var colIdx   = colModel.findColumnIndex(this.dataIndex);
            var col      = colModel.columns[colIdx];
            var val      = this.isTrue(record.data[col.dataIndex]);
            
            var editEvent = this.createEditEvent(
                                this.grid, record, index, col,
                                !val,
                                 val
                             );
            record.set(this.dataIndex, editEvent.value);
            this.grid.fireEvent('afteredit',editEvent);
        }
    },
    
    onKeyPress : function (aEv)
    {        
        var sm    = this.getSelectionModel();
        if (!sm.getSelectedCell) return;
        
        var cell  = sm.getSelectedCell();
        var col   = this.getColumnModel().columns[cell[1]];
        
        /* If selected cell is checkColumn check the toggle keys. */
        if (col.checkColumn)
        {
            var key = aEv.getKey();
            //Space or ENTER
            if ( (key == 32) || (key == 13) )
            {
                var record = this.store.getAt(cell[0]);
                var val = col.isTrue(record.data[col.dataIndex]);
                //Save the value in the record and trigger the event.
                var editEvent = col.createEditEvent(
                                    this, record, cell[0], col, 
                                    !val,
                                     val
                                );
                record.set(col.dataIndex, editEvent.value);
                this.fireEvent('afteredit',editEvent);
            }
        }
    },

    isTrue : function(aVal)
    {
        //v could be 1 or 0 as number
        if (typeof(aVal)=='string') //could be "true"|"false"|"1"|"0" as string
        {
            if (isFinite(aVal))
                aVal = parseInt(aVal);
            else
                aVal = (aVal != "false") ? 1 : 0;
        }
        //Or Could be native true|false|1|0
        
        return aVal;
    },
    
    renderer : function(v, p, record)
    {
        v = this.isTrue(v);
        
        p.css += ' x-grid3-check-col-td'; 
        return '<div class="x-grid3-check-col'+(v?'-on':'')+' x-grid3-cc-'+this.id+'"> </div>';
    }
};

// register ptype
Ext.reg('checkcolumn', Ext.ux.grid.CheckColumn);

// backwards compat
Ext.grid.CheckColumn = Ext.ux.grid.CheckColumn;