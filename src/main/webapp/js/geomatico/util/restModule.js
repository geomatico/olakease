geomatico.util.restModule = function() {
   return {
      basePath : null,
      getBaseAjaxElement : function(entityPath, method) {
         return {
            type : method,
            url : this.basePath + entityPath,
            beforeSend : function() {
               $(document).trigger("before-send");
            },
            error : new function(xhr, ajaxOptions, thrownError) {
               $(document).trigger("after-receive");
               var errorMessage = "General error";
               if (typeof xhr != 'undefined') {
                  errorMessage = xhr.responseText;
               }
               $(document).trigger("error", errorMessage);
            }
         };
      },
      write : function(method, entityPath, entity) {
         ajaxElement = this.getBaseAjaxElement(entityPath, method);
         ajaxElement.contentType = "application/json";
         ajaxElement.data = $.toJSON(entity);
         ajaxElement.success = function(data) {
            $(document).trigger("after-receive");
            $(document).trigger("get", entityPath);
         };
         $.ajax(ajaxElement);
      },
      /**
       * @basePath All the operations are relative to this URL.
       */
      init : function(basePath) {
         var this_ = this;
         this.basePath = basePath;

         $(document).bind(
            'get',
            function(event, entityPath) {
               ajaxElement = $.proxy(this_.getBaseAjaxElement, this_)(
                  entityPath, "GET");
               ajaxElement.success = function(data) {
                  $(document).trigger("after-receive");
                  $(document).trigger("get-received", [ entityPath, data ]);
               };
               $.ajax(ajaxElement);
            });
         $(document).bind('put', function(event, entityPath, entity) {
            $.proxy(this_.write, this_)("PUT", entityPath, entity);
         });
         $(document).bind('post', function(event, entityPath, entity) {
            $.proxy(this_.write, this_)("POST", entityPath, entity);
         });
         $(document).bind(
            'delete',
            function(event, collectionPath, entityPath) {
               ajaxElement = $.proxy(this_.getBaseAjaxElement, this_)(
                  entityPath, "DELETE");
               ajaxElement.success = function(data) {
                  $(document).trigger("after-receive");
                  $(document).trigger("get", collectionPath);
               };
               $.ajax(ajaxElement);
            });
      }
   };
};