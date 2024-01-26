import axios from 'axios'
import React, { useEffect, useState } from 'react'
import CardInformation from '../../common/card/CardInformation';
import style from './Home.module.css'
import Header from '../../common/header/Header';
import { Button } from '@mui/material';
import CreateEmpleadoModal from '../../common/createEmpleadoModal/CreateEmpleadoModal';

const Home = () => {

    const [empleados, setEmpleados] = useState([])
    const [dispatchActivo, setDispatchActivo] = useState(false)
    const [open, setOpen] = useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    useEffect( () => {
        axios
        .get("http://localhost:5000/empleados")
        .then((res) => setEmpleados(res.data))
        .catch((err) => console.log(err));

        setDispatchActivo(false)
    }, [dispatchActivo]);

    const handleActivo = (empleado) => {
      axios
      .patch(`http://localhost:5000/empleados/${empleado.id}`, {activo: !empleado.activo})
      .then((res) => setDispatchActivo(true))
      .catch((err) => console.log(err))

    }

  return (
    <>
    <header><Header/></header>
    <div className={style.containerCrearEmpleado}>
    <Button onClick={handleOpen}>Crear empleado</Button>
    </div>
    <CreateEmpleadoModal open={open} handleClose={handleClose}/>
      <div className={style.containerCards}>
        {
          empleados.map( (empleado) => {
            return ( 
              <CardInformation empleado={empleado} key={empleado.id} handleActivo ={handleActivo}/>
            )
          })
        }
      </div>

    </>
  )
}

export default Home
