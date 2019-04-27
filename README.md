#### Docker stack jBPMn Workbench
based on drools workbench / business central 7.15.0.Final

### Install
* Windows  
run the *run-dev.bat*
* Linux  
run *docker-compose build && docker-compose up -d*

__Please wait for servers to load.__

### Login URL
[local workbench](http://localhost:28080/drools-wb/kie-wb.jsp)
(http://localhost:28080/drools-wb)

### Usernames and passwords
|USER        |PASSWORD    |ROLE    |
| ---------- |------------|-----------|
|admin       |admin       |admin,analyst,kiemgmt
|krisv       |krisv       |admin,analyst
|john        |john        |analyst,Accounting,PM
|sales-rep   |sales-rep   |analyst,sales
|katy        |katy        |analyst,HR
|jack        |jack        |analyst,IT

### Import repository
If you have an existing BPM project, you have to import the project by the projects repository. You will need to have READ access to this repo.

### Check out repository
The default checkout-URL is *ssh://admin@localhost:8001/MySpace* followed by the name of your BPM project. If this is "my-test", you have to checkout with this URL:  
```
ssh://admin@localhost:8001/MySpace/my-test
```    

