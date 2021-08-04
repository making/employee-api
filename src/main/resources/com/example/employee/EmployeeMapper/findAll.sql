SELECT id, first_name, last_name, salary
FROM employee
WHERE 1 = 1
    /*[# th:if="${firstName} != null"]*/
    /*[# mb:bind="firstNamePattern=|%${#likes.escapeWildcard(#strings.toLowerCase(firstName))}%|" /]*/
  AND lower(first_name) LIKE /*[# mb:p="firstNamePattern"]*/ '%a%' /*[/]*/
    /*[/]*/
    /*[# th:if="${lastName} != null"]*/
    /*[# mb:bind="lastNamePattern=|%${#likes.escapeWildcard(#strings.toLowerCase(lastName))}%|" /]*/
  AND lower(last_name) LIKE /*[# mb:p="lastNamePattern"]*/ '%c%' /*[/]*/
    /*[/]*/
    /*[# th:if="${salaryMin} != null"]*/
  AND salary >= /*[# mb:p="salaryMin"]*/ 50000 /*[/]*/
    /*[/]*/
    /*[# th:if="${salaryMax} != null"]*/
  AND salary <= /*[# mb:p="salaryMax"]*/ 100000 /*[/]*/
    /*[/]*/
ORDER BY first_name, id