Flow of application is as follows:
1. Application takes the input argument for the config xml file.
2. Parses the config xml file using the JAXB parser and then fetch the input xml file and output file name.
3. Then it reads the input stats from the input file and starts processing one line at a time.
4. For each record fetched from the input file, app validates if record is valid or not. 
5. If record is invalid then it logs the message and ignores the record and moves to next records.
6. If record is valid then app fetches the information of province, city, school, grade and population and processes it i.e.
	- Check if Province already exists 
		a). if yes add the population to existing province
		b). if no create the new province and add the population to the new province.
	- Check if City already exists in the province 
		a).	if yes add the population to existing city of that province.
		b).	if no create the new city in the province and add the population to the new city of that province.
	- Check if School already exists in the City of the province 
		a).	if yes add the population to existing School of that city for that province.
		b).	if no create the new School in the city of that province and add the population to the new School of that city for that province.
7. Once all the records are done then it creates the output file.
8. Then updates the output file summarizing total population per school, city and province.

Ecliplse Version: eclipse-java-luna-SR2-win32-x86_64
Java Version: "1.8.0_162"

Note: Application uses log4j2 for logging and its jar are in dist folder.

Usage:
Usage   : java -Dlog4j.configurationFile=File:<log4j2XmlFile> -jar <jarFile> <inputXmlFile>
Example : java -Dlog4j.configurationFile=File:etc/log4j2.xml -jar pwbe.jar etc/input.xml

Sample run:
C:\PopulationWithBrownEyes\dist>runPWBE.bat
C:\PopulationWithBrownEyes\dist>java -Dlog4j.configurationFile=File:etc/log4j2.xml -jar bin/pwbe.jar etc/config.xml
INFO    Starting the execution ...
INFO    Loading the configuation XML file: etc/config.xml
INFO    Configuration XML loaded successfully.
INFO    Input file name: input//inputStats.txt
INFO    Processing the statistics of people from province, city & school.
INFO    Processing record: ON   Waterloo        School1 K1000
INFO    Processing record: ON   Waterloo        School1 12000
INFO    Processing record: ON   Waterloo        School2 83000
INFO    Processing record: ON   Kitchener       School3 K0043
INFO    Processing record: ON   Kitchener       School3 10057
INFO    Processing record: ON   Kitchener       School3 20003
INFO    Processing record: ON   Kitchener       School3 30005
INFO    Processing record: ON   Kitchener       School3 40000
INFO    Generating the report.
INFO    School3              108
INFO    Kitchener            108
INFO    School2              3000
INFO    School1              3000
INFO    Waterloo             6000
INFO    ON                   6108
INFO    Execution completed successfully.

