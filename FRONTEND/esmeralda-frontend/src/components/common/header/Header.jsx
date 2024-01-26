import React from 'react'
import "./Header.css"
import logoHA from "../../../img/LogoHA.png";
import { Button} from '@mui/material';

const Header = () => {
  return (
    <div className="headerContainer">
      <div className="logoContainer">
        <img alt= "Logo HA" className="logoHA" src={logoHA}/>
      </div>
      <div className='buttonsContainer'>
        <Button variant='contained' color="error">
          Iniciar Sesión 
        </Button>
        <Button variant='contained' color="error">
          Crear cuenta
        </Button>
      </div>
    </div>
  )
}

export default Header
