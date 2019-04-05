# mk_jl_price_test
# Pre-Requisite
	Download and install Java 8 from https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
	Download and install (eclipse IDE for Enterprise Java Developer) latest eclipse from https://www.eclipse.org/downloads/ (latest version Eclipse IDE 2019-03)
	Open eclipse in new workspace

# Eclipse Plugins
	Go to Help -> Eclipse Market Place and search for "Spring" and install "Spring Tools 4 - for Spring Boot" plugin
	Go to Help -> Eclipse Market Place and search for "Groovy" and install "Groovy Development Tools" plugin

# Import Maven Project into Eclipse
	Download code to local file system from Github repository
	In Eclipse Go to File -> Import and Search for Maven and select "Existing Maven Projects" and import downloaded project into workspace by browsing to local file system

# Run the application
	Open Boot Dashboard and run the application or Right click "com.jl.pricing.JLPricingApplication" and run as "Spring Boot Application"

# Unit Testing
	Open "com.jl.pricing.engine.filter.ProductPricingFilterSpecification" and run it as Junit Test case to execute Unit Test cases for class "com.jl.pricing.engine.filter.ProductPricingFilter"
	Open "com.jl.pricing.engine.mapper.PriceMapperSpecification" and run it as Junit Test case to execute Unit Test cases for class "com.jl.pricing.engine.mapper.PriceMapper"
	Open "com.jl.pricing.engine.mapper.ColorSwatchMapperSpecification" and run it as Junit Test case to execute Unit Test cases for class "com.jl.pricing.engine.mapper.ColorSwatchMapper"

# Integration Testing
	Open "com.jl.pricing.ProductPricingIntegrationSpecification" and run it as Junit Test case to execute integration Test case (Please ensure application is running before executing this)
	
# Browser Testing
	Open Chrome browser & go to http://localhost:8080 this will display help page to test the code via browser
