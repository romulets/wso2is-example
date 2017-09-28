import React from 'react'
import Authenticate from './Authenticate'
import { CookiesProvider } from 'react-cookie'
import { BrowserRouter as Router, Route } from 'react-router-dom'

export default function App () {
  return (
    <CookiesProvider>
      <Router>
        <Route path='/' component={Authenticate} />
      </Router>
    </CookiesProvider>
  )
}
