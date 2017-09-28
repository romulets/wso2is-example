import { withCookies } from 'react-cookie'
import React, { Component } from 'react'
import Authorizate from './Authorizate'
import queryString from 'query-string'

class Authenticate extends Component {

  constructor (props) {
    super(props)

    this.state = {
      authenticated: false,
      authUri: '',
      code: ''
    }
  }

  componentDidMount () {
    const { cookies } = this.props
    const { code } = queryString.parse(this.props.location.search)

    if (code || cookies.get('accessToken')) {
      this.setState({code, authenticated: true})
    } else {
      this.fetchAuthUri()
    }
  }

  fetchAuthUri () {
    const callbackUri = 'http://localhost:8080/wso2Example'
    const authPath = 'http://localhost:8080/wso2Example/api/authenticate'
    const requestUri = `${authPath}?callbackUri=${callbackUri}`

    fetch(requestUri)
      .then(response => response.json())
      .then(message => {
        this.setState({ authUri: message.authPage })
      }).catch(error => {
        console.error(error)
      })
  }

  render () {
    if (this.state.authenticated) {
      return (
        <Authorizate code={this.state.code} />
      )
    } else {
      return (
        <a href={this.state.authUri}> <button> Authenticate on wso2 </button> </a>
      )
    }
  }
}

export default withCookies(Authenticate)
