geomatico.app = function() {
   return {
      init : function() {
         geomatico.communication().init();
         geomatico.entityList().init("PROJECTS", "project", "projects",
            function(project) {
               return project.name + ": " + project.description;
            });
         geomatico.entityList().init("DEVELOPERS", "developer", "developers",
            function(developer) {
               return developer.name;
            });
         $(document).trigger("get", "projects");
         $(document).trigger("get", "developers");
      }
   };
};