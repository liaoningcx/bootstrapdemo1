--------------------------------
架构搭建需要改动的地方
1.spring扫描目录(spring-config.xml/spring-config-mvc.xml)
1.spring-dao扫描目录(spring-config-dao.xml)
2.mybatis加载map自动生成（mybatis-config.xml）
3.mapper.xml位置调换下
--------------------------------


原 autocode

触发生成代码方法
http://localhost:9910/mysqlMetadataGenerator/generator-downLoad


MysqlMetadataGeneratorController.generatorDownLoad() --  generator-downLoad
next
MysqlMetadataGeneratorServiceImpl.generatorDownLoad()



json串，demo：


{
    "tableSchema": "autocode",
    "tableSqlName": "autocode",
    "tableComment": "wewe",
    "packagePath": "sd",
    "author": "qwqw",
    "mysqlMetadataGeneratorJson": "[{"tableSchema":"autocode","tableSqlName":"autocode","columnName":"id","dataType":"bigint","columnKey":"PRI","extra":"auto_increment","columnComment":"PK_AutoUp","flag":null,"filter":null},{"tableSchema":"autocode","tableSqlName":"autocode","columnName":"name","dataType":"varchar","columnKey":"","extra":"","columnComment":"名称","flag":null,"filter":null}]",
    "generatorType": "1",
    "projectDirectory": null
}
--mysqlMetadataGeneratorJson
[
{"tableSchema":"autocode","tableSqlName":"autocode","columnName":"id","dataType":"bigint","columnKey":"PRI","extra":"auto_increment","columnComment":"PK_AutoUp","flag":null,"filter":null}
,{"tableSchema":"autocode","tableSqlName":"autocode","columnName":"name","dataType":"varchar","columnKey":"","extra":"","columnComment":"名称","flag":null,"filter":null}
]

----------------------------------------------------------------------------------------
mine


