import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Layout from './pages/Layout';
import Home from './pages/Home';
import Customers from './pages/Customers';

function App() {
  return (
    <div className="App">
    <BrowserRouter>
      <Routes>
        <Route path={"/"} element={<Layout/>}>
          <Route index element={<Home/>}/>
          <Route path={"customers"} element={<Customers/>}/>
        </Route>
      </Routes>
    </BrowserRouter>
    </div>
  );
}

export default App;
