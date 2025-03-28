import { useState } from 'react';
import axios from 'axios';

function SearchEmployee() {
  const [searchType, setSearchType] = useState('id');
  const [searchValue, setSearchValue] = useState('');
  const [searchResult, setSearchResult] = useState(null);
  const [error, setError] = useState('');

  const handleSearch = async (e) => {
    e.preventDefault();
    setError('');
    setSearchResult(null);

    try {
      let response;
      if (searchType === 'id') {
        response = await axios.get(`http://localhost:8080/api/employees/${searchValue}`);
      } else if (searchType === 'email') {
        response = await axios.get(`http://localhost:8080/api/employees/email/${searchValue}`);
      }
      setSearchResult(response.data);
    } catch (error) {
      setError('Employee not found');
      console.error('Error searching employee:', error);
    }
  };

  return (
    <div className="container mx-auto px-4 py-8">
      <h2 className="text-2xl font-bold mb-6">Search Employee</h2>

      <div className="bg-white p-6 rounded-lg shadow-md mb-8">
        <form onSubmit={handleSearch} className="flex gap-4 items-end">
          <div className="flex-1">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              Search By
            </label>
            <select
              className="w-full p-2 border rounded"
              value={searchType}
              onChange={(e) => setSearchType(e.target.value)}
            >
              <option value="id">ID</option>
              <option value="email">Email</option>
            </select>
          </div>
          <div className="flex-1">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              Search Value
            </label>
            <input
              type={searchType === 'email' ? 'email' : 'text'}
              className="w-full p-2 border rounded"
              value={searchValue}
              onChange={(e) => setSearchValue(e.target.value)}
              required
            />
          </div>
          <button
            type="submit"
            className="bg-purple-600 text-white py-2 px-4 rounded hover:bg-purple-700"
          >
            Search
          </button>
        </form>
      </div>

      {error && (
        <div className="text-red-600 mb-4">{error}</div>
      )}

      {searchResult && (
        <div className="bg-white p-6 rounded-lg shadow-md">
          <h3 className="text-xl font-semibold mb-4">Employee Details</h3>
          <div className="grid grid-cols-2 gap-4">
            <div>
              <p className="font-bold">Name:</p>
              <p>{`${searchResult.firstName} ${searchResult.lastName}`}</p>
            </div>
            <div>
              <p className="font-bold">Email:</p>
              <p>{searchResult.email}</p>
            </div>
            <div>
              <p className="font-bold">Role:</p>
              <p>{searchResult.role}</p>
            </div>
            <div>
              <p className="font-bold">Salary:</p>
              <p>${searchResult.salary}</p>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default SearchEmployee;