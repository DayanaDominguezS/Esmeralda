import { Box, Modal, TextField} from '@mui/material'
import "./Create.empleado.css"

const CreateEmpleadoModal = ({open, handleClose}) => {
  
    return (
      <div>
        <Modal
          open={open}
          onClose={handleClose}
          aria-labelledby="modal-modal-title"
          aria-describedby="modal-modal-description"
        >
          <Box className="style" >
            <h1>CREAR EMPLEADO</h1>
            <form className="boxCreateEmpleado">
                <TextField id="outlined-basic" label="Primer nombre" variant="outlined" fullWidth/>
                <TextField id="outlined-basic" label="Segundo nombre" variant="outlined" fullWidth/>
                <TextField id="outlined-basic" label="Primer apellido" variant="outlined" fullWidth/>
                <TextField id="outlined-basic" label="Segundo apellido" variant="outlined" fullWidth/>
                <TextField id="outlined-basic" label="Fecha de nacimiento" variant="outlined" fullWidth/>
                <TextField id="outlined-basic" label="Documento de identidad" variant="outlined" fullWidth/>
                <TextField id="outlined-basic" label="Lugar de expedición del documento de identidad" variant="outlined" fullWidth/>
                <TextField id="outlined-basic" label="Correo electrónico" variant="outlined" fullWidth/>
                <TextField id="outlined-basic" label="Contraseña" variant="outlined" fullWidth/>
                <TextField id="outlined-basic" label="Celular" variant="outlined" fullWidth/>
                <TextField id="outlined-basic" label="Celular" variant="outlined" fullWidth/>
            </form>
          </Box>
        </Modal>
      </div>
    );
}

export default CreateEmpleadoModal
