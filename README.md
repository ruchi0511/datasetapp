# datasetapp
DatasetApp is a Spring boot application to upload daw jones index dataset, example dataset (daw_jones_index.data) can be downloaded from http://archive.ics.uci.edu/ml/datasets/Dow+Jones+Index#. 
The dataset is a comma separated data so the application assumed that user will provide content-type header as text/csv while uploading the dataset.
Users can also query the dataset on stock ticker, they can also add a record, which is assumed to be nothing but a single row in a csv file.

Spring boot is a stand alone application and can be brought up by running the jar file. Postman app was used to test the application apis.

Future considerations -
1. Application introduces the logging at a very basic level, better logging can be added in future iterations.
2. Better exception handling using advices.
3. Add test cases - integration test at controller level, component tests at service level and unit tests cases.
4. In future iteration application may also be improved with the better way for adding a record in terms of input provided.
5. Application can also add a UI for showing the search results to the user.
