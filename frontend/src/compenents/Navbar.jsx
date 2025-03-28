import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Home, Users, Building2, Search, LogOut, LogIn, UserPlus } from 'lucide-react';
import clsx from 'clsx';

function Navbar({ isAuthenticated, setIsAuthenticated }) {
  const navigate = useNavigate();
  const location = useLocation();

  const handleLogout = () => {
    setIsAuthenticated(false);
    navigate('/login');
  };

  const isActive = (path) => location.pathname === path;

  return (
    <nav className="bg-purple-600 text-white shadow-lg">
      <div className="container mx-auto px-4">
        <div className="flex justify-between items-center h-16">
          <Link to="/" className="flex items-center space-x-2">
            <Building2 className="h-6 w-6" />
            <span className="text-xl font-bold">Employee Management System</span>
          </Link>
          <div className="flex items-center space-x-4">
            {isAuthenticated ? (
              <>
                <Link
                  to="/"
                  className={clsx(
                    "flex items-center space-x-1 px-3 py-2 rounded-lg transition-colors duration-200",
                    isActive('/') ? "bg-purple-700 text-white" : "hover:bg-purple-700"
                  )}
                >
                  <Home className="h-5 w-5" />
                  <span>Home</span>
                </Link>
                <Link
                  to="/departments"
                  className={clsx(
                    "flex items-center space-x-1 px-3 py-2 rounded-lg transition-colors duration-200",
                    isActive('/departments') ? "bg-purple-700 text-white" : "hover:bg-purple-700"
                  )}
                >
                  <Building2 className="h-5 w-5" />
                  <span>Departments</span>
                </Link>
                <Link
                  to="/employees"
                  className={clsx(
                    "flex items-center space-x-1 px-3 py-2 rounded-lg transition-colors duration-200",
                    isActive('/employees') ? "bg-purple-700 text-white" : "hover:bg-purple-700"
                  )}
                >
                  <Users className="h-5 w-5" />
                  <span>Employees</span>
                </Link>
                <Link
                  to="/search"
                  className={clsx(
                    "flex items-center space-x-1 px-3 py-2 rounded-lg transition-colors duration-200",
                    isActive('/search') ? "bg-purple-700 text-white" : "hover:bg-purple-700"
                  )}
                >
                  <Search className="h-5 w-5" />
                  <span>Search</span>
                </Link>
                <button
                  onClick={handleLogout}
                  className="flex items-center space-x-1 px-3 py-2 rounded-lg hover:bg-purple-700 transition-colors duration-200"
                >
                  <LogOut className="h-5 w-5" />
                  <span>Logout</span>
                </button>
              </>
            ) : (
              <>
                <Link
                  to="/login"
                  className={clsx(
                    "flex items-center space-x-1 px-3 py-2 rounded-lg transition-colors duration-200",
                    isActive('/login') ? "bg-purple-700 text-white" : "hover:bg-purple-700"
                  )}
                >
                  <LogIn className="h-5 w-5" />
                  <span>Login</span>
                </Link>
                <Link
                  to="/register"
                  className={clsx(
                    "flex items-center space-x-1 px-3 py-2 rounded-lg transition-colors duration-200",
                    isActive('/register') ? "bg-purple-700 text-white" : "hover:bg-purple-700"
                  )}
                >
                  <UserPlus className="h-5 w-5" />
                  <span>Register</span>
                </Link>
              </>
            )}
          </div>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;