geomatico.communication = function () {
   return {
      init : function() {
         $(document).bind('get', function(event, entity) {
            $.ajax({
               type : 'GET',
               url : '/olakease/api/' + entity,
               beforeSend : function() {
                  // this is where we append a loading image
               },
               success : function(data) {
                  $(document).trigger(entity + '-received', [data]);
               },
               error : function() {
                  // failed request; give feedback to user
                  $console.log('error');
               }
            });
         });
      }
   };
};