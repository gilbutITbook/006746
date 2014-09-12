beans {
    xmlns jdbc:"http://www.springframework.org/schema/jdbc"
    xmlns util:"http://www.springframework.org/schema/util"
	
    jdbc.'embedded-database' (id:"dataSource") {
        jdbc.script(location:"classpath:/META-INF/data/schema.sql")
        jdbc.script(location:"classpath:/META-INF/data/data.sql")
    }
	
    util.map(id:"sql") {
        entry(key:"query", value:
            """
            select d.documentId, d.name, d.location, d.description as doc_desc,
            d.typeId, d.created, d.modified,
            t.name as type_name, t.description as type_desc, t.extension from
            documents d
            join types t
            on d.typeId = t.typeId
            """)
        entry(key:"find", value:"""
            select d.documentId, d.name, d.location, d.description as doc_desc,
            d.typeId, d.created, d.modified,
            t.name as type_name, t.description as type_desc, t.extension from
            documents d
            join types t
            on d.typeId = t.typeId
            where d.documentId = ?
            """)
    }
}
