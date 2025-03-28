import { Link } from 'react-router-dom';

function Home() {
  return (
    <div className="container mx-auto px-4 py-8">
      <h1 className="text-3xl font-bold mb-8 text-center">Welcome to Employee Management System</h1>
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        <Link to="/departments">
          <div className="bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition-shadow">
            <h2 className="text-xl font-semibold mb-4">Manage Departments</h2>
            <p className="text-gray-600">Create and manage departments in your organization</p>
          </div>
        </Link>
        <Link to="/employees">
          <div className="bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition-shadow">
            <h2 className="text-xl font-semibold mb-4">Manage Employees</h2>
            <p className="text-gray-600">Add, update, or remove employees from departments</p>
          </div>
        </Link>
        <Link to="/search">
          <div className="bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition-shadow">
            <h2 className="text-xl font-semibold mb-4">Search Employee</h2>
            <p className="text-gray-600">Search for employees by department, name, or ID</p>
          </div>
        </Link>
      </div>
    </div>
  );
}

export default Home;