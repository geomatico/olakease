geomatico.communication = function() {
   return {
      basePath : null,
      fncBeforeSend : function() {
         // this is where we append a loading image
         console.log("Sending request");
      },
      fncError : function() {
         // failed request; give feedback to user
         console.log("Error");
      },
      getBaseAjaxElement : function(entityPath, method) {
         return {
            type : method,
            url : this.basePath + entityPath,
            beforeSend : this.fncBeforeSend,
            error : this.fncError
         };
      },
      write : function(method, entityPath, entity) {
         ajaxElement = this.getBaseAjaxElement(entityPath, method,
            this.fncBeforeSend, this.fncError);
         ajaxElement.contentType = "application/json";
         ajaxElement.data = $.toJSON(entity);
         ajaxElement.success = function(data) {
            $(document).trigger("get", entityPath);
         };
         $.ajax(ajaxElement);
      },
      init : function(basePath, fncBeforeSend, fncError) {
         var this_ = this;
         this.basePath = basePath;
         if (fncBeforeSend == null) {
            this.fncBeforeSend = fncBeforeSend;
         }
         if (fncError == null) {
            this.fncError = fncError;
         }

         $(document).bind(
            'get',
            function(event, entityPath) {
               ajaxElement = $.proxy(this_.getBaseAjaxElement, this_)(
                  entityPath, "GET");
               ajaxElement.success = function(data) {
                  $(document).trigger(entityPath + '-received', [ data ]);
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
                  $(document).trigger("get", collectionPath);
               };
               $.ajax(ajaxElement);
            });
      }
   };
};