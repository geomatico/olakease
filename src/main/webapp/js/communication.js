geomatico.communication = function() {
   return {
      beforeSend : function() {
         // this is where we append a loading image
         console.log("Sending request");
      },
      error : function() {
         // failed request; give feedback to user
         console.log("Error");
      },
      getBaseAjaxElement : function(basePath, entityPath, method,
         fncBeforeSend, fncError) {
         return {
            type : method,
            url : basePath + entityPath,
            beforeSend : (fncBeforeSend == null) ? this.beforeSend
               : fncBeforeSend,
            error : (fncError == null) ? this.error : fncError
         };
      },
      write : function(method, basePath, entityPath, entity, fncBeforeSend,
         fncError) {
         ajaxElement = this.getBaseAjaxElement(basePath, entityPath, method,
            fncBeforeSend, fncError);
         ajaxElement.contentType = "application/json";
         ajaxElement.data = $.toJSON(entity);
         ajaxElement.success = function(data) {
            $(document).trigger("get", entityPath);
         };
         $.ajax(ajaxElement);
      },
      init : function(basePath, fncBeforeSend, fncError) {
         var this_ = this;
         $(document).bind(
            'get',
            function(event, entityPath) {
               ajaxElement = $.proxy(this_.getBaseAjaxElement, this_)(basePath,
                  entityPath, "GET", fncBeforeSend, fncError);
               ajaxElement.success = function(data) {
                  $(document).trigger(entityPath + '-received', [ data ]);
               };
               $.ajax(ajaxElement);
            });
         $(document).bind(
            'put',
            function(event, entityPath, entity) {
               $.proxy(this_.write, this_)("PUT", basePath, entityPath, entity,
                  fncBeforeSend, fncError);
            });
         $(document).bind(
            'post',
            function(event, entityPath, entity) {
               $.proxy(this_.write, this_)("POST", basePath, entityPath,
                  entity, fncBeforeSend, fncError);
            });
         $(document).bind(
            'delete',
            function(event, collectionPath, entityPath) {
               ajaxElement = $.proxy(this_.getBaseAjaxElement, this_)(basePath,
                  entityPath, "DELETE", fncBeforeSend, fncError);
               ajaxElement.success = function(data) {
                  $(document).trigger("get", collectionPath);
               };
               $.ajax(ajaxElement);
            });
      }
   };
};