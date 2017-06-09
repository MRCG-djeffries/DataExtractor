# OpenClinica Data Extraction tool
Extracts OC XML downlaods to a visual table builder in MS Access and allows exports to relational databases (MS Access, SQLite and SQLserevr).<br/>
No installation required, runs from an MS Access database.
<br/>
## DataExtractor folder
Contains the MS Access database (DataExtractor.accdb), jar library and a test XML OpenClinica download.<br/>
Export all three to same folder and run DataExtractor.accdb <br/>
Full details and example in the user guide. 
<br/>
## Validation Folder
The user gudie describes how to validate the tool for any OpenClinica CRF. <br/>
Example files are provided. 
<br/>
### DataExtractorProject Folder
Contains the java source code for the XML extraction library using Virtual Token Descriptor (http://vtd-xml.sourceforge.net/). <br/>
A compiled version of the code is contained in DataExtractor/OC_ACCESS_EXTRACTOR_V1.0.jar
