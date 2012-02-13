TActiveRecord = function(fields) {
    this.id = null;
       
    for(var field in fields) {
        if(fields.hasOwnProperty(field))
            this[field] = fields[field];
    }
}
    
TActiveRecord.prototype.save = function() {
    $('#output').html("i am saving");
}
    
TActiveRecord.prototype.destroy = function() {
    
}

TActiveRecord.open = function(modelClass, id) {
    $('#output').html("open " + id);
    var inst = new modelClass();
    inst.id = id;
    return inst;
}
 
    

    
    
/* test start */
/*    
p = new Person({name: "Anton"});
p.save();



$('#open_btn').click(function(){
    var p = Person.open(2);
    
});
*/