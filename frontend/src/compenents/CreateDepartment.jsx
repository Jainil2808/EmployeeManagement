import { useState } from 'react';
import { Building2 } from 'lucide-react';
import axios from 'axios';

function CreateDepartment() {
  const [formData, setFormData] = useState({
    name: '',
    description: '',
    userId: localStorage.getItem('userId') // Get userId from local storage
  });

  const handleSubmit = async (e) => {
    e.preventDefault();

        // Verify userId is available
        if (!formData.userId) {
          alert('User authentication failed. Please log in again.');
          return;
        }

    try {
      const response = await axios.post('http://localhost:8080/api/departments/', formData, {
        headers: {
          'Authorization': `Bearer ${localStorage.getItem('token')}` // Add token for authentication
        }
      });
      
      setFormData({ name: '', description: '', userId: formData.userId });
      alert('Department created successfully!');
    } catch (error) {
      console.error('Error creating department:', error);
      alert('Failed to create department');
    }
  };

  return (
    <div className="min-h-screen bg-gray-50 py-8">
      <div className="max-w-3xl mx-auto">
        <div className="text-center mb-8">
          <h1 className="text-3xl font-bold flex items-center justify-center gap-3">
            <Building2 className="h-8 w-8 text-purple-600" />
            Departments
          </h1>
        </div>

        <div className="bg-white rounded-2xl shadow-lg p-8">
          <h2 className="text-2xl font-semibold mb-6 text-center">Add New Department</h2>
          
          <form onSubmit={handleSubmit} className="space-y-6">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Name
              </label>
              <input
                type="text"
                className="w-full px-4 py-3 rounded-lg border border-gray-300 focus:ring-2 focus:ring-purple-500 focus:border-purple-500 transition-colors"
                placeholder="Enter department name"
                value={formData.name}
                onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                required
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Description
              </label>
              <textarea
                className="w-full px-4 py-3 rounded-lg border border-gray-300 focus:ring-2 focus:ring-purple-500 focus:border-purple-500 transition-colors"
                placeholder="Enter department description"
                rows="4"
                value={formData.description}
                onChange={(e) => setFormData({ ...formData, description: e.target.value })}
                required
              />
            </div>

            <button
              type="submit"
              className="w-full bg-purple-600 text-white py-3 px-6 rounded-lg hover:bg-purple-700 focus:outline-none focus:ring-2 focus:ring-purple-500 focus:ring-offset-2 transition-colors duration-200"
            >
              Add Department
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default CreateDepartment;