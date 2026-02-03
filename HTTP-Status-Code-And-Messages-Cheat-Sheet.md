# HTTP Status Codes Cheat Sheet

This is a list of HTTP status codes and their meanings.   
> This list does not include all status codes, just some common ones.

### 1xx: Informational

-   **100 Continue**: Continue with the request.

### 2xx: Successful

-   **200 OK**: Request succeeded.
-   **201 Created**: Resource created.
-   **204 No Content**: Request processed successfully, but no content is returned.

### 3xx: Redirection

-   **301 Moved Permanently**: Resource moved to a new URL.
-   **302 Found**: Temporary redirection.
-   **304 Not Modified**: Resource not modified.

### 4xx: Client Error

-   **400 Bad Request**: Invalid request syntax.
-   **401 Unauthorized**: Authentication required.
-   **403 Forbidden**: Access denied.
-   **404 Not Found**: Resource not found.
-   **405 Method Not Allowed**: Method not allowed on resource.
-   **408 Request Timeout**: Server timed out waiting for the request.
-   **409 Conflict**: Request conflict with current state.
-   **410 Gone**: Resource permanently removed.
-   **413 Payload Too Large**: Request body too large.
-   **429 Too Many Requests**: Too many requests in a given time.

### 5xx: Server Error

-   **500 Internal Server Error**: Generic server error.
-   **501 Not Implemented**: Server does not recognize method.
-   **502 Bad Gateway**: Invalid response from upstream server.
-   **503 Service Unavailable**: Server unavailable.
-   **504 Gateway Timeout**: Upstream server timeout.

Link: [https://developer.mozilla.org/en-US/docs/Web/HTTP/Reference/Status](https://developer.mozilla.org/en-US/docs/Web/HTTP/Reference/Status)