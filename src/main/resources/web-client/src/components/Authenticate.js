import React, { Component } from 'react'

export default class Authenticate extends Component {

  state = {
    authUri: ""
  }

  componentDidMount () {
    this.fetchAuthUri()
  }

  fetchAuthUri () {

    const callbackUri = "http://localhost:8080/wso2Example"
    const consumerKey = ""
    const authPath    = "http://localhost:8080/wso2Example/api/authenticate"
    const requestUri  = `${authPath}?consumerKey=${consumerKey}&callbackUri=${callbackUri}`

    console.log(requestUri)

    fetch(requestUri)
      .then(response => response.json())
      .then(message => {
        this.setState({ authUri: message.authPage })
      })
      .catch(error => {
        console.error(error)
      })
  }

  render () {
    return (
      <a href={this.state.authUri}> Fazer Login no wso2 </a>
    )
  }
}
