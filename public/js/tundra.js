TActiveRecord = function(fields) {
    this.Id = null;
       
    for(var field in fields) {
        if(fields.hasOwnProperty(field))
            this[field] = fields[field];
    }
}
    
TActiveRecord.prototype.save = function(successCallback) {
    if (this.id == null) // let's create object
    {
        var url = "objects/" + tundraProjectId + "/" + this.constructor.tableName;
        $.post(url, JSON.stringify(this), successCallback, "json");
    }
}

TActiveRecord.getAll = function(modelClass, successCallback) {
    var url = "objects/" + tundraProjectId + "/" + modelClass.tableName;
    $.get(url, {}, function(data) {
      objects = [];
      for (var i=0; i< data.length; i++)
      {
        obj = new modelClass(data[i]);
        objects.push(obj);
      }
      successCallback.call(document, objects);
    }, "json");
     
}

TActiveRecord.deleteId = function(modelClass, id, successCallback) {
    var url = "objects/" + tundraProjectId + "/" + modelClass.tableName + "/" + id;
    $.ajax({
      type: "DELETE",
      url: url,
      success: function(){
        successCallback.call(document);
      },
      dataType: "json"
    });
}

TActiveRecord.method = function(modelClass, methodName, methodBody) {
    modelClass.prototype[methodName] = methodBody;
}
 
TActiveRecord.prototype.destroy = function() {
    
}

TActiveRecord.open = function(modelClass, id) {
    var url = "objects/" + tundraProjectId + "/" + modelClass + "/" + id;

    $.get(url,{}, function(data) {
	        
	    }, "json");

    var inst = new modelClass();
    inst.id = id;
    return inst;
}

