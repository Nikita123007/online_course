<c:if test="${prev != null}">
    <h3><a href="${servletName}offset=${prev}">Prev</a></h3>
</c:if>
<c:if test="${next != null}">
    <h3><a href="${servletName}offset=${next}">Next</a></h3>
</c:if>