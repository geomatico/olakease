geomatico.app = function() {
   return {
      init : function() {
         geomatico.communication().init("/olakease/api/");
         geomatico.entityList().init("PROJECTS", "project", "projects",
            [ "name", "description" ]);
         geomatico.entityList().init("DEVELOPERS", "developer", "developers",
            [ "name" ]);
         $(document).trigger("get", "projects");
         $(document).trigger("get", "developers");
      }
   };
};