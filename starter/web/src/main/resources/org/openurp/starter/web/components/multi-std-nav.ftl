[#ftl]
[#if students?size>1]
<ul class="nav nav-tabs nav-tabs-compact">
[#list students as s]
  <li class="nav-item">
    <a href="${b.url("!index?studentId="+s.id)}" class="nav-link [#if student==s]active[/#if]" onclick="return bg.Go(this,null)">${s.state.grade.name}级 ${s.project.name} ${(s.state.major.name)!}</a>
  </li>
[/#list]
</ul>
[/#if]
