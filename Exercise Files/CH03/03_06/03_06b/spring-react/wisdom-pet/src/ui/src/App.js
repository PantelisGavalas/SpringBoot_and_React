import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Layout from './pages/Layout';
import Home from './pages/Home';
import Customers from './pages/Customers';
import Vendors from './pages/Vendors';
import Products from './pages/Products';
import Services from './pages/Services';

function App() {
  return (
    <div className="App">
    <BrowserRouter>
      <Routes>
        <Route path={"/"} element={<Layout/>}>
          <Route index element={<Home/>}/>
          <Route path={"customers"} element={<Customers/>}/>
          <Route path={"vendors"} element={<Vendors/>}/>
          <Route path={"products"} element={<Products/>}/>
          <Route path={"services"} element={<Services/>}/>
        </Route>
      </Routes>
    </BrowserRouter>
    </div>
  );
}

export default App;
