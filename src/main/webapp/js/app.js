geomatico.app = function() {
   return {
      init : function() {
         geomatico.util.restModule().init("/olakease/api/");
         var body = $("body");
         geomatico.entityList().init(body, "PROJECTS", "project", "projects",
            [ "name", "description" ]);
         geomatico.entityList().init(body, "DEVELOPERS", "developer",
            "developers", [ "name" ]);
         $(document).trigger("get", "projects");
         $(document).trigger("get", "developers");

         $(document).bind(
            "projects-selected",
            function(event, selectedEntity) {
               geomatico.entityForm().init(body, "Project XXX", "projects/lol",
                  selectedEntity, [ "name", "description" ]);
            });
         $(document).bind(
            "projects-new",
            function(event) {
               geomatico.entityForm().init(body, "New project", "projects/lol",
                  {}, [ "name", "description" ]);
            });
         $(document).bind(
            "developers-selected",
            function(event, selectedEntity) {
               geomatico.entityForm().init(body, "Developer XXX",
                  "developers/lol", selectedEntity, [ "name" ]);
            });
         $(document).bind(
            "developers-new",
            function(event) {
               geomatico.entityForm().init(body, "New developer",
                  "developers/lol", {}, [ "name" ]);
            });

      }
   };
};