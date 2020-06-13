# Paging_library_example
Simple example of paging library using mvvm architecture pattern.

# Paging Library:

-Paging library makes it easier for our app to step by step load information as needed from a data source without overloading the device or waiting to long.
 
 *Advantages:
 
 -App consumes less network bandwith and fewer system resourses.
 
 -App work efficiently, data loading time has minimized.
 
 -During updates and refreshes app behaves normal and respond quickly.
 
 -paging library easier to code.
 
- There are three main classes to consider.

  1. Paged list adapter - extends the RecyclerView.Adapter class.
  
  2. Paged List - the page list for loading the data automatically. This allows us to configure the initial load size, the page size, and also the per page distance.
  
  3. Data source - the data source is an interface to provide the data step by step. If you are using paging library with Room, you can automatically generate
					a data source. if you are using paging with remote data source you need to implement subclass of data source.
					
*Project Setup:

- For implement paging library, we need to add paging library dependencies to the gradle.

    def paging_version = "1.0.1"
    implementation "android.arch.paging:runtime:$paging_version"   

-Repository class is handles by data source class in paging library.

-Data source class is the base class comes with paging library for data loading.

-Paging library also has 3 implementation of the base data source class.

-If we are going to pass page number as a query parameter in the request we can use PageKeyDataSource(Super Class of data source class).

-If we are going to fetch data using a key belong to an item, As an example, In our project we are going to fetch data using the id of movies, we can use 
 
 ItemKeyedDataSource.
 
-If we have a data source with a known fixed size, and we going to fetch items with arbitary positions we can use PositionalDataSource.

* Data Source Factory Class(DataSource.Factory class will be super class here):

- Data source factory class is responsible for retriving the data using the data source and it will be used by other paging library components.

-The purpose of DataSourceFactory class is constructing a data source instace and return it as live data.

-PagedList is act between data source and page list adapter.

-PageList adapter uses DiffUtill as a parameter to calculate data difference and to do all the updates for us.

-DiffUtill is a utility class introduced with appCompact purpose of diffutill was to make recycler view updatesefficint and easier.


 
