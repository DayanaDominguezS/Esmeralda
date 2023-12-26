import React from 'react'
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardMedia from '@mui/material/CardMedia';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Avatar from '@mui/material/Avatar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import { red } from '@mui/material/colors';
import FavoriteIcon from '@mui/icons-material/Favorite';
import ShareIcon from '@mui/icons-material/Share';

const CardInformation = ({empleado, handleActivo}) => {

  const letraAvatar1 = empleado.primerApellido.charAt(0)
  const letraAvatar2 = empleado.segundoApellido.charAt(0)

  return (
    <Card sx={{ width: 300, height: 600 }}>
      <CardHeader
        avatar={
          <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">
            {letraAvatar1 + letraAvatar2}
          </Avatar>
        }
        title= {empleado.primerApellido + " " + empleado.primerNombre}
        subheader={empleado.numeroDocIdentidad}
      />
      <CardMedia
        component="img"
        height="194"
        image={empleado.image}
        alt="Foto empleado"
      />
      <CardActions disableSpacing>
        <IconButton aria-label="add to favorites">
          <FavoriteIcon color={empleado.activo ? "error" : "disabled"} onClick={()=>handleActivo(empleado)}/>
        </IconButton>
        <IconButton aria-label="share">
          <ShareIcon />
        </IconButton>
      </CardActions>
        <CardContent>
          <Typography paragraph>
            Reseña
          </Typography>
          <Typography paragraph>
            {empleado.descripcion}
          </Typography>
        </CardContent>
    </Card>
  )
}

export default CardInformation
