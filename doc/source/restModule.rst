REST communication module
=========================

Listened events
---------------

* ``get``: Receives the entity path relative to the base URL. Produces a ``[entity path]-received`` event in response. For example::

	$(document).trigger("get", "projects");
	$(document).trigger("get", "developers");

* ``put``: Receives the entity path relative to the base URL and the entity to put. Produces a ``get`` event for the same entity path. For example::

	$(document).trigger("put", "projects", projectWithModifications);

* ``post``: Receives the entity path relative to the base URL and the entity to post. Produces a ``get`` event for the same entity path. For example::

	$(document).trigger("post", "projects", newProject);

* ``delete``: Receives the path of the entity collection and the path of the entity to remove in that collection, both of them relative to the base URL. Produces a ``get`` event for the same entity path. For example::

	$(document).trigger("delete", "projects", "projects/23");

Generated events
----------------

* ``error``: If the call to the server fails. Contains a message of error.

* ``before-send``: Invoked just before making the call.

* ``after-receive``: Invoked just after the call response has been received, either with success or with failure.

* ``get-received``: After successful ``get``. contains the entity path used in the ``get`` event as parameter and data returned by the server.

* ``get``: After successful ``post``, ``put`` or ``delete``.