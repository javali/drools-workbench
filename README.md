## Docker stack jBPMn Workbench
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

### Usage
Please check the tests for usage examples.
```
de.javali.jbpm.workbench.ProcessTest
```    

### Check out repository
The default checkout-URL is *ssh://admin@localhost:8001/MySpace* followed by the name of your BPM project. If this is "my-test", you have to checkout with this URL:  
```
ssh://admin@localhost:8001/MySpace/my-test
```    
- your work is automatically pushed to this repo so you should not write to it from outside as long as you know what you are doing

#### Usecase "existing" (concept)
*there is an existing jBPM-project you want to import, edit and export to e.g.CI*  
__use a second remote location in your git repo for the CI-repo__

- pull your repo.master (workbench-master)
- create branch in you repo, e.g. "changes-from-workbench"
- push "changes-from-workbench" to ci-repo
- create pullrequest "changes-from-workbench" to master in CI-repo

