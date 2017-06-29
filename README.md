# OpenClinica Data Extraction tool
Extracts OC XML downloads to a visual table builder in MS Access and allows exports to relational databases (MS Access, SQLite and SQLserver).<br/>
No installation required, runs from an MS Access database.
<br/>
## DataExtractor folder
Contains the MS Access database (DataExtractor.accdb), an executable jar file (OC_ACCESS_EXTRACTOR_V1.0.jar) and two test XML OpenClinica extractions.<br/>
Download the MS Access application and the jar file to the same folder and run DataExtractor.accdb <br/>
Full details and examples in the user guide. 
<br/>
## Validation Folder
The user gudie describes how to validate the tool for any OpenClinica CRF. <br/>
Example files are provided.
<br/>
### DataExtractorJavaProject Folder
Contains the java source code for the XML extraction library using Virtual Token Descriptor (http://vtd-xml.sourceforge.net/). <br/>
A compiled version of the code is contained in DataExtractor/OC_ACCESS_EXTRACTOR_V1.0.jar
