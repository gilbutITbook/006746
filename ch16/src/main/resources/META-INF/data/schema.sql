CREATE TABLE types (
  typeId varchar(36) NOT NULL,
  name varchar(45) NOT NULL,
  description varchar(255) DEFAULT NULL,
  extension varchar(10) DEFAULT NULL,
  PRIMARY KEY (typeId)
);

CREATE TABLE documents (
  documentId varchar(36) NOT NULL,
  name varchar(255) NOT NULL,
  location varchar(600) NOT NULL,
  description varchar(600),
  typeId varchar(36) NOT NULL,
  created datetime NOT NULL,
  modified datetime NOT NULL,
  PRIMARY KEY (documentId),
  CONSTRAINT documentType FOREIGN KEY (typeId) REFERENCES types (typeId)
);

CREATE TABLE users (
  userId varchar(36) NOT NULL,
  email varchar(100) NOT NULL,
  password varchar(45) NOT NULL,
  name varchar(45) NOT NULL,
  userdocumentId varchar(36) DEFAULT NULL,
  PRIMARY KEY (userId)
);

CREATE TABLE userdocument (
  userdocumentId varchar(36) NOT NULL,
  userId varchar(36) DEFAULT NULL,
  documentId varchar(36) DEFAULT NULL,
  PRIMARY KEY (userdocumentId),
  CONSTRAINT users FOREIGN KEY (userId) REFERENCES users (userId) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT documents FOREIGN KEY (documentId) REFERENCES documents (documentId) ON DELETE NO ACTION ON UPDATE NO ACTION
);
