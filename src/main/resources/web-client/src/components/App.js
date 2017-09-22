import React from 'react'
import Authenticate from './Authenticate'
import { BrowserRouter as Router, Route } from 'react-router-dom'

export default function App () {
  return (
    <Router>
      <div>
        <Route path="/" component={Authenticate}/>
      </div>
    </Router>
  )
}
