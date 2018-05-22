<c:if test="${prev != null}">
    <a class="design" href="${servletName}offset=${prev}&count=${count}">Prev</a>
</c:if>
<c:if test="${next != null}">
    <a href="${servletName}offset=${next}&count=${count}">Next</a>
</c:if>
<div style="float: right;margin-bottom: 80px;">
    <label style="font-size: medium" for="entitiesCount">Entities count</label>
    <select name="entitiesCount" id="entitiesCount" onchange="OnChangeCount()">
        <option <c:if test="${count == 10}">selected</c:if>>10</option>
        <option <c:if test="${count == 15}">selected</c:if>>15</option>
        <option <c:if test="${count == 20}">selected</c:if>>20</option>
    </select>
</div>
<script>
    function OnChangeCount(){
        select = $('#entitiesCount')[0];
        count = select.options[select.selectedIndex].value;
        document.location.href=document.location.protocol+'//'+document.location.host+"/${servletName}offset=${offset}"+'&count='+count;
    }
</script>