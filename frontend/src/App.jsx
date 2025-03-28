import { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Navbar from './components/Navbar';
import Login from './components/Login';
import Register from './components/Register';
import Home from './components/Home';
import DepartmentList from './components/CreateDepartment';
import EmployeeList from './components/ManageEmployee';
import SearchEmployee from './components/SearchEmployee';
import './App.css';
import CreateDepartment from './components/CreateDepartment';
import ManageEmployee from './components/ManageEmployee';

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  return (
    <Router>
      <div className="min-h-screen bg-gray-50">
        <Navbar isAuthenticated={isAuthenticated} setIsAuthenticated={setIsAuthenticated} />
        <div className="container mx-auto px-4 py-8">
          <Routes>
            <Route path="/login" element={<Login setIsAuthenticated={setIsAuthenticated} />} />
            <Route path="/register" element={<Register />} />
            <Route
              path="/"
              element={isAuthenticated ? <Home /> : <Navigate to="/login" />}
            />
            <Route
              path="/departments"
              element={isAuthenticated ? <CreateDepartment /> : <Navigate to="/login" />}
            />
            <Route
              path="/employees"
              element={isAuthenticated ? <ManageEmployee /> : <Navigate to="/login" />}
            />
            <Route
              path="/search"
              element={isAuthenticated ? <SearchEmployee /> : <Navigate to="/login" />}
            />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;