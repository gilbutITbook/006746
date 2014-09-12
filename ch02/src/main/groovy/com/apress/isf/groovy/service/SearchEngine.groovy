package com.apress.isf.groovy.service

import com.apress.isf.groovy.model.Type

interface SearchEngine {
	def findByType(Type documentType)	
	def listAll()
}
