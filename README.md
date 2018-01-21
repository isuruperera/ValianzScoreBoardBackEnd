# ValianzScoreBoardBackEnd
## API

### Request Data
#### Endpoint
  http://host:9393/api/requestData
  
   * Request: {"requestType":[int],"teamID":[int]}
   * Method:POST
   * requestType:1 = Get All Game Data
   * requestType:2 = Get All game data for a single team
   * requestType:3 = Get all data about games
   
   *Should send basic auth information in the header.*
      * default user: user 
      * defalt password:password
   
### Update Data
#### Endpoint
  http://host:9393/api/updateData
  
   * Request: {"requestType":[int],"teamID":[int],"gameID":[int],"score":[float]}
   * Method:POST
   * requestType:1 = Insert new game score
   * requestType:2 = Update game score
   
   *Should send auth information in the header.*
      *Roles Allowed: ROLE_ADMIN

    
