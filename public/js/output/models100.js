/* auto-generated file */


	Person = function(fields) {
	    TActiveRecord.call(this, fields);
	}   
	    
	Person.prototype = new TActiveRecord();
	Person.prototype.constructor = Person;    
	
	Person.open = function(id) {
	    return TActiveRecord.open(this, id);
	}

	Address = function(fields) {
	    TActiveRecord.call(this, fields);
	}   
	    
	Address.prototype = new TActiveRecord();
	Address.prototype.constructor = Address;    
	
	Address.open = function(id) {
	    return TActiveRecord.open(this, id);
	}
