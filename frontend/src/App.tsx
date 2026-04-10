import { useEffect, useState } from 'react';

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
    in_library: boolean;
    year: number;
    quality: string;
  };

  const [books, setBooks] = useState<BookDTO[]>([]);
  const [selectedBook, setSelectedBook] = useState<BookDTO | null>(null);
  const [copies, setCopies] = useState<CopyDTO[]>([]);
  const [showModal, setShowModal] = useState(false);

  // Load books
  useEffect(() => {
    fetch('/api/AllBooksWithAuthors')
      .then((res) => res.json())
      .then((data) => setBooks(data))
      .catch((err) => console.error(err));
  }, []);

  // Load copies when button clicked
  const loadCopies = (isbn: string, book: BookDTO) => {
    fetch(`/api/copies/${isbn}`)
      .then((res) => res.json())
      .then((data) => {
        setCopies(data);
        setSelectedBook(book);
        setShowModal(true);
      })
      .catch((err) => console.error(err));
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
                  {copy.in_library ? 'In Library' : 'Checked Out'} - {copy.year}
                  {copy.year}
                </li>
              ))}
            </ul>

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
