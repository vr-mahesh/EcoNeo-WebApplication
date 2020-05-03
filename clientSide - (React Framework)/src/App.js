import React from 'react';
import './App.scss';
import Counter from '../src/components/counter/counter';
import { Route, Switch } from 'react-router-dom';
import Signup from '../src/components/signup/signup';
import Login from '../src/components/login/login';
import Profile from '../src/components/profile/profile';
import TicketCenter from '../src/components/ticketcenter/ticketcenter';
import SocialFeed from '../src/components/socialfeed/socialfeed';
import MyProfile from '../src/components/myprofile/myprofile';
import { Navbar, Nav, NavItem, NavDropdown, MenuItem } from 'react-bootstrap';
import MyDashboard from './components/mydashboard/mydashboard';
import AuditRecord from './components/auditrecord/auditrecord';
import Ecodashboard from './components/ecodashboard/ecodashboard';
import Logout from './components/logout/logout';

function App() {
  var a =10;
  return (
    <div className="container-fluid paddin0">
    <Navbar  sticky="top" collapseOnSelect expand="lg" bg="dark" variant="dark">
  <Navbar.Brand href="#home">Eco-Neo</Navbar.Brand>
  <Navbar.Toggle aria-controls="responsive-navbar-nav" />
  <Navbar.Collapse id="responsive-navbar-nav">
    <Nav className="mr-auto">

    <Nav.Link href="/">Home</Nav.Link>
    {
      (sessionStorage.getItem("id") === "" || sessionStorage.getItem("id") === undefined || sessionStorage.getItem("id") ===null)
      ?
      <Nav.Link href="/login">Login</Nav.Link>
    
      :
      <Nav.Link href="/logout">logout</Nav.Link>
    }
     
    {
      (sessionStorage.getItem("id") === "" || sessionStorage.getItem("id") === undefined || sessionStorage.getItem("id") ===null)
      ?
      <Nav.Link href="/signup">Sign Up</Nav.Link>
     
      :
      <Nav.Link href="/logout"></Nav.Link>
    }
     
     
     
      <Nav.Link href="/socialfeed">Social Feed</Nav.Link>

      {
      (sessionStorage.getItem("type") === "user")
      ?
     
      <Nav.Link href="/ticketcenter">Ticket Center</Nav.Link>
      :
      <Nav.Link href="/"></Nav.Link>
    }
      
      <Nav.Link href="/myprofile">My Profile</Nav.Link>

      {
      (sessionStorage.getItem("type") === "enterprise")
      ?
      <Nav.Link href="/mydashboard">My Dashboard</Nav.Link>
     
      :
      <Nav.Link href="/"></Nav.Link>
    }
     

      
      <Nav.Link href="/ecodashboard">Eco Dashboard</Nav.Link>

    </Nav>
  </Navbar.Collapse>
</Navbar>
    <Switch>
     <Route path="/signup" component={Signup} />
     <Route path="/login" component={Login} />
     <Route path="/socialfeed" component={SocialFeed} />
     <Route path="/profile" component={Profile} />
     <Route path="/ticketcenter" component={TicketCenter} />
     <Route path="/myprofile" component={MyProfile} />
     <Route path="/mydashboard" component={MyDashboard} />
     <Route path="/auditrecord" component={AuditRecord} />
     <Route path="/ecodashboard" component={Ecodashboard} />
     <Route path="/logout" component={Logout} />
     <Route path="/" component={Counter} />
     
     </Switch>
    </div>
  );
}

export default App;
