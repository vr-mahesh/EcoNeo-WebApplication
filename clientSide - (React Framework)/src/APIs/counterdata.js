import axios from 'axios';
export const getuserdata = async () => {
    try {
      const res = await axios.get('http://localhost:8080/neo/api/user/mahesh.vr/password/mahi');
      
 const user = (
    ({ role, fullName,userName}) =>
        ({ role, fullName, userName })
)(res.data);
return user;
} catch (error) {
      console.error(error)
    }
  }