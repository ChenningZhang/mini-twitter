# mini-twitter
## To Run the Application
* clone or download this repository.
* Check the schema file(located at mini-twitter/src/main/resources) and make sure the database is set up successfully.
* In Eclipse, select Run -> Run As -> Maven build...
* In Goals, type in 'tomcat7:run', shown in the picture below.
	![alt tag](https://raw.githubusercontent.com/ChenningZhang/mini-twitter/master/readme_images/tomcat_inst.png)
* Wait till the Application starts, open up browser, type in url "localhost:8080/"
* It should redirect you to the homepage, which shows the API of this Application.
	![alt tag](https://raw.githubusercontent.com/ChenningZhang/mini-twitter/master/readme_images/api_form.png)

## Notes
* Remember to "log in" a user first before trying to read his tweets, find his followers, let him follow/unfollow others. 
	Otherwise it will throw an Unauthorized Exception, or even ResourceNotFound Exception if the user does not exist in the database.
* For sample runs and results, please refer to the screenshots in mini-twitter/sampleruns