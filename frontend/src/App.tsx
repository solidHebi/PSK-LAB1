import { useEffect, useState } from 'react';

import axios from 'axios';

function App() {
  type BookDTO = {
    isbn: string;
    title: string;
    authors: string[];
    genre: string;
    year: number;
  };

  type CopyDTO = {
    id: number;
    inInventory: boolean;
    quality: string;
    year: number;
  };

  type CopyCreateDTO = {
    isbn: string;
    year: number;
    quality: string;
    inInventory: boolean;
  };
  const [copyForm, setCopyForm] = useState<CopyCreateDTO>({
    isbn: '',
    year: 0,
    quality: '',
    inInventory: false,
  });

  const [books, setBooks] = useState<BookDTO[]>([]);
  const [selectedBook, setSelectedBook] = useState<BookDTO | null>(null);
  const [copies, setCopies] = useState<CopyDTO[]>([]);
  const [showModal, setShowModal] = useState(false);
  const [editingCopyId, setEditingCopyId] = useState<number | null>(null);

  // Load books
  useEffect(() => {
    axios
      .get('/api/AllBooksWithAuthors')
      .then((response) => {
        setBooks(response.data);
        console.log(response.data);
      })
      .catch((err) => console.error(err));
  }, []);

  // Load copies when button clicked
  const loadCopies = (isbn: string, book: BookDTO) => {
    axios
      .get(`/api/copies/${isbn}`)
      .then((response) => {
        setCopies(response.data);
        setSelectedBook(book);
        setShowModal(true);

        setCopyForm({
          isbn: isbn,
          year: 0,
          quality: '',
          inInventory: false,
        });
      })
      .catch((err) => console.error(err));
  };
  const addCopy = async () => {
    try {
      await axios.post('/api/copies', copyForm);

      if (selectedBook) {
        loadCopies(selectedBook.isbn, selectedBook);
      }

      console.log('Copy created');
    } catch (err) {
      console.error(err);
    }
  };
  const updateCopy = async () => {
    try {

      await axios.put(
          `/api/copies/${editingCopyId}`,
          copyForm
      );

      alert('Copy updated');

    } catch (err: any) {

      if (err.response?.status === 409) {

        alert(
            'This copy was modified by another user. Reloading latest data.'
        );

        if (selectedBook) {
          loadCopies(selectedBook.isbn, selectedBook);
        }

        return;
      }

      console.error(err);
    }
  };
  return (
    <div style={{ padding: '20px' }}>
      <h1>Books</h1>

      {/* TABLE */}
      <table border={1} cellPadding={10}>
        <thead>
          <tr>
            <th>Title</th>
            <th>Authors</th>
            <th>Genre</th>
            <th>Year</th>
            <th>Action</th>
          </tr>
        </thead>

        <tbody>
          {books.map((book) => (
            <tr key={book.isbn}>
              <td>{book.title}</td>
              <td>{book.authors.join(', ')}</td>
              <td>{book.genre}</td>
              <td>{book.year}</td>
              <td>
                <button onClick={() => loadCopies(book.isbn, book)}>
                  View Copies
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* MODAL */}
      {showModal && selectedBook && (
        <div style={modalStyle}>
          <div style={modalContentStyle}>
            <h2>{selectedBook.title} - Copies</h2>

            <ul>
              {copies.map((copy) => (
                <li key={copy.id}>
                  Copy #{copy.id} - {copy.quality} -{' '}
                  {copy.inInventory ? 'In Library' : 'Checked Out'} -{' '}
                  {copy.year}

                  <button
                      onClick={() => {
                        setEditingCopyId(copy.id);

                        setCopyForm({
                          isbn: selectedBook.isbn,
                          year: copy.year,
                          quality: copy.quality,
                          inInventory: copy.inInventory,
                        });
                      }}
                      style={{ marginLeft: '10px' }}
                  >
                    Edit
                  </button>
                </li>
              ))}
            </ul>

            <hr />

            <h3>Add New Copy</h3>

            <div>
              <label>Year:</label>
              <input
                type='number'
                value={copyForm.year || ''}
                onChange={(e) =>
                  setCopyForm({ ...copyForm, year: Number(e.target.value) })
                }
              />
            </div>

            <div>
              <label>Quality:</label>
              <select
                value={copyForm.quality}
                onChange={(e) =>
                  setCopyForm({ ...copyForm, quality: e.target.value })
                }
              >
                <option value=''>Select quality</option>
                <option value='NEW'>NEW</option>
                <option value='GOOD'>GOOD</option>
                <option value='FAIR'>FAIR</option>
                <option value='POOR'>POOR</option>
              </select>
            </div>

            <div>
              <label>In Inventory:</label>
              <input
                type='checkbox'
                checked={copyForm.inInventory}
                onChange={(e) =>
                  setCopyForm({ ...copyForm, inInventory: e.target.checked })
                }
              />
            </div>

            <br />

            {editingCopyId ? (
                <button onClick={updateCopy}>Update Copy</button>
            ) : (
                <button onClick={addCopy}>Add Copy</button>
            )}
            <button onClick={() => setShowModal(false)}>Close</button>
          </div>
        </div>
      )}
    </div>
  );
}

export default App;

const modalStyle: React.CSSProperties = {
  position: 'fixed',
  top: 0,
  left: 0,
  width: '100%',
  height: '100%',
  backgroundColor: 'rgba(0,0,0,0.5)',
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
};

const modalContentStyle: React.CSSProperties = {
  background: 'black',
  padding: '20px',
  borderRadius: '8px',
  minWidth: '300px',
};
