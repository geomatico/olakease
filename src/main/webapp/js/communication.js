geomatico.communication = function () {
   return {
      init : function() {
         $(document).bind('get', function(event, entityPath) {
            $.ajax({
               type : 'GET',
               url : '/olakease/api/' + entityPath,
               beforeSend : function() {
                  // this is where we append a loading image
               },
               success : function(data) {
                  $(document).trigger(entityPath + '-received', [data]);
               },
               error : function() {
                  // failed request; give feedback to user
                  console.log('error');
               }
            });
         });
         $(document).bind('put', function(event, entityPath, entity) {
            $.ajax({
               type : 'PUT',
               url : '/olakease/api/' + entityPath,
               contentType: "application/json",
               data : $.toJSON(entity),
               beforeSend : function() {
                  // this is where we append a loading image
               },
               success : function(data) {
                  $(document).trigger("get", entityPath);
               },
               error : function() {
                  // failed request; give feedback to user
                  console.log('error');
               }
            });
         });
      }
   };
};