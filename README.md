### Steps to the issue

1. run the file `TestTestIssueApplication1` or `TestTestIssueApplication2`
2. check logs you will find this query is printed`select x1_0.id,x1_0.name from xtable x1_0 offset ? rows fetch first ? rows only` however dialect is `org.hibernate.community.dialect.PostgreSQLLegacyDialect`


### what i expect 
it should print sql like this `select x1_0.id,x1_0.name from Xtable x1_0 limit ? offset ?` which is the old way as `PostgreSQLLegacyDialect` use `LimitOffsetLimitHandler`