import axios from 'axios';
/// Authenticate User
var config = {
  headers: {"authtoken": sessionStorage.getItem("authtoken")}
};
export async  function  getuserdata(username,password)  {// async function expression used as an IIFE
  try {
    const res = await axios.get('http://localhost:8080/neo/api/adduser/user/'+username+'/password/'+password,config);
     console.log(res.data);
     console.log("USer login successful");
const user = (
  ({ role, fullName,userName}) =>
      ({ role, fullName, userName })
)(res.data);
return res.data;
} catch (error) {
    console.error(error)
  }
}



/// Authenticate enterprise
export async  function  getenterprisedata(username,password)  {// async function expression used as an IIFE
  try {
    const res = await axios.get('http://localhost:8080/neo/enterprise/auth/user/'+username+'/password/'+password);
     console.log(res.data);
     console.log("Enterprise login successful");
const user = (
  ({ role, fullName,userName}) =>
      ({ role, fullName, userName })
)(res.data);
return res.data;
} catch (error) {
    console.error(error)
  }
}


/// Register User
export const registerUser = async (user) => {
   try {
    const res = await axios.post('http://localhost:8080/neo/api/user/add',user,config);
return res.data;
} catch (error) {
    console.error(error)
  }
}

export const generatepdf = async () => {
  try {
   const res = await axios.get('http://localhost:8080/neo/auditrecord/pdf',config);
return res.data;
} catch (error) {
   console.error(error)
 }
}

// register enterprise
export const registerEnterprise = async (enterprise) => {
  try {
   const res = await axios.post('http://localhost:8080/neo/enterprise/addenterprise',enterprise,config);
return res.data;
} catch (error) {
   console.error(error)
 }
}

// raise ticket
export const raiseTicket = async (ticket) => {
  try {
   const res = await axios.post('http://localhost:8080/neo/enterprise/addticket',ticket,config);
return res.data;
} catch (error) {
   console.error(error)
 }
}



//// upload images
export const update_image= async(formData) =>{
  try {
    const res = await axios.post('http://localhost:8080/neo/upload/updateimage',formData,config);
    console.log(res.data);
  return res.data;
  } catch (error) {
    console.error(error)
  }
  }

  export const del_story= async(storyId) =>{
    try {
      const res = await axios.get('http://localhost:8080/neo/upload/deletestory/'+storyId,config);
      console.log(res.data);
    return res.data;
    } catch (error) {
      console.error(error)
    }
    }
  

//// upload images
export const upload_image= async(formData) =>{
try {
  const res = await axios.post('http://localhost:8080/neo/upload/image',formData,config);
  console.log(res.data);
return res.data;
} catch (error) {
  console.error(error)
}
}

export async  function  getProfileData(userId,type)  {
try {
  let res ;
  if(type=="user"){
     res = await axios.get('http://localhost:8080/neo/api/user/'+userId,config);
  }
  else{
     res = await axios.get('http://localhost:8080/neo/enterprise/getenterprise/'+userId,config);
  }
   
     console.log(res.data);
return res.data;
} catch (error) {
    console.error(error)
  }
}

export async  function  profileStories(userId)  {
  try {
    const res = await axios.get('http://localhost:8080/neo/upload/getProfileStories/'+userId,config)
    
    return res.data;

  } catch (error) {
      console.error(error)
    }
  }

  
  
  
  /// Add Comment
export const addLike = async (like) => {
  try {
   const res = await axios.post('http://localhost:8080/neo/upload/addlike',like,config);
return res.data;
} catch (error) {
   console.error(error) 
 }
}


  /// Add Comment
export const addComment = async (comment) => {
  try {
   const res = await axios.post('http://localhost:8080/neo/upload/addcomment',comment,config);
return res.data;
} catch (error) {
   console.error(error) 
 }
}

export const delete_comment = async (comment_id) => {
  try {
    const formData = new FormData();
    formData.append("comment_id",comment_id);
   const res = await axios.post('http://localhost:8080/neo/media/comment/delete',formData,config);
return res.data;
} catch (error) {
   console.error(error) 
 }
}


export const getTicketsbyId = async (id) => {
  try {
  
   const res = await axios.get('http://localhost:8080/neo/tickets/getTicketsby_EID/'+id,config);
return res.data;
} catch (error) {
   console.error(error) 
 }
}


export const updateResponseMessage = async (id,text) => {
  try {
  let obj={}
  obj.ticketId=id;
  obj.responseMessage=text;
   const res = await axios.post('http://localhost:8080/neo/tickets/updateResponseMessage',obj,config);
return res.data;
} catch (error) {
   console.error(error) 
 }
}


export const submit_audit = async (dataa) => {
  try {
   const res = await axios.post('http://localhost:8080/neo/auditrecord/add',dataa,config);
return res.data;
} catch (error) {
   console.error(error) 
 }
}


export const close_ticket = async (dataa) => {
  try {
   let obj={};
    obj.id=dataa;
   const res = await axios.post('http://localhost:8080/neo/tickets/closeticket',obj,config);
return res.data;
} catch (error) {
   console.error(error) 
 }
}


export const getallenterprise = async () => {
  try {
 
   const res = await axios.get('http://localhost:8080/neo/enterprise/getallenterprise/10',config);
return res.data;
} catch (error) {
   console.error(error) 
 }
}




export const getAllcompanies = async () => {
  try {
 
   const res = await axios.get('http://localhost:8080/neo/enterprise/getallenterprise/10',config);
return res.data;
} catch (error) {
   console.error(error) 
 }
}


export const load_auditreport = async () => {
  try {
 
   const res = await axios.get('http://localhost:8080/neo/auditrecord/getallauditrecords/10',config);
return res.data;
} catch (error) {
   console.error(error) 
 }
}


