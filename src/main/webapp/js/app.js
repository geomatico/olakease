geomatico.app = function () {
   return {
      init : function() {
         geomatico.communication().init();
         geomatico.projectPanel().init();
         $(document).trigger('get', 'projects');
      }
   };
};